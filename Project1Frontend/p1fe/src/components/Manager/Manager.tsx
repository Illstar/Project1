import { useState } from "react"
import "./Manager.css"
import { ReimbursementInterface } from "../interfaces/ReimbursementInterface"
import { useNavigate } from "react-router-dom"
import { UserInterface } from "../interfaces/UserInterface"
import axios from "axios"
import { state } from "../globalData/store"

export const Manager: React.FC=()=> {

    
    //A variable to store user input for finding a Reimbursement
    //TODO: modify
    const [userInput, setUserInput] = useState<number>(0)

    //we need to store Reimbursement state to use when rendering the ReimbursementComponent
    const [reimbursement, setReimbursement] = useState<ReimbursementInterface>({
        amount:0,
        status:"",
        description:""
    })

    //defining a state object for our user data
    const[user, setUser] = useState<UserInterface>({
        username:"",
        password:""
    })

    const navigate = useNavigate()

    //a function that stores the user input (Which we need for our GET request)
    const gatherInput = (input:any) => {
        setUserInput(input.target.value) //set the userInput to what's in the input box
    }

    //a function that sends a GET request based on user input
    //TODO: modify, was getPokemon
    const getReimbursement = async () => {

        console.log(userInput)

        //sending our request to pokeAPI using the userInput as the pokemon id to search for
        //const response = await axios.get("https://pokeapi.co/api/v2/pokemon/" + userInput)

        //console.log(response.data) //response.data will give us the incoming data from the request

        //let's set our pokemon state with the incoming data
        //setPokemon((pokemon) => ({...pokemon, name:response.data.name})) //only changing the name
        //setPokemon((pokemon) => ({...pokemon, image:response.data.sprites.front_default})) //only changing the image

        //what's that?^^ when we have state as entire objects, it's tricky to change just one value...
        //we can use the ...spread operator to say "keep the entire state object as is, but change this one thing"

    }

    ////////////////////////////////////////////////////////////////////////////////

    //testing logout feature
    const logout = async () => {

        //TODO: We could (and should) validate user input here as well as backend

        //Send a POST request to the backend for login
        //NOTE: with credentials is what lets us save/send user session info
        const response = await axios.post("http://localhost:8080/users/logout",
        user,
        {withCredentials:true}) //this is important for cookies to be stored    
        .then((response) => {

            //if the logout was successful, reset global state
            state.userSessionData.userId = 0;
            state.userSessionData.username = "";
            state.userSessionData.firstName = "";
            state.userSessionData.lastName = "";
            state.userSessionData.role = ""; 

            console.log(state.user)

            alert(response.data)

            navigate("/")

            

        })
        .catch((error) => {

            //if the logout was unsuccessful, alert the user
            alert(error.response.data)
        })


        
    }
    ////////////////////////////////////////////////////////////////////////////////

    return (
        <div className="manager">
            <div className="text-container">
                <h1>Manager Landing Page</h1>

                <div className="button-container">

                    <button className="nav-button" onClick={() => navigate("/all_r")}>See All Reimbursements</button>
                    <button className="nav-button" onClick={() => navigate("/all_p_r")}>See All Pending Reimbursements</button>
                    <button className="nav-button" onClick={() => navigate("/resolve_r")}>Resolve A Reimbursement</button>
                    <button className="nav-button" onClick={() => navigate("/all_users")}>See All Users</button>
                    <button className="nav-button" onClick={() => navigate("/delete_user")}>Delete A User</button>

                </div>
                {/*
                <h3>Create a New Reimbursement</h3>
                <h3>See All Reimbursements</h3>
                <h3>See Only Pending Reimbursements</h3>
                */}

                {/* 
                <input type="number" placeholder="Enter Reimbursement Amount" onChange={gatherInput}/>
                <button className="poke-button">Submit Reimbursement</button>
                */}

                {/* 
                <input type="number" placeholder="Enter Pokemon ID" onChange={gatherInput}/>
                <button className="poke-button" onClick={getPokemon}>Find Pokemon</button>


                <div className="poke-container">
                    {pokemon.name ? <button className="poke-button" onClick={catchPokemon}>catch</button> : ''}
                    <Pokemon {...pokemon}></Pokemon>
                </div>
                */}

                <div><button className="logout-button" onClick={logout}>Logout</button></div>

            </div>
        </div>
    );
}