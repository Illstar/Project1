import { useNavigate } from "react-router-dom"
import "./Login.css"
import { useState } from "react"
import { UserInterface } from "../interfaces/UserInterface"
import axios from "axios"
import { state } from "../globalData/store"

export const Login: React.FC = () => {

    //defining a state object for our user data
    const[user, setUser] = useState<UserInterface>({
        username:"",
        password:""
    })
    
    //We need a useNavigate hook to allow use to nagivate between compoennts
    //No more manual URL changes!
    const navigate = useNavigate()

    //function to store input box values
    const storeValues = (input:any) => {

        //if the input that has changed is the "username" input, change the value of username in the user state object

        if(input.target.name === "username"){
            setUser((user) => ({...user, username:input.target.value}))
        } else {
            setUser((user) => ({...user, password:input.target.value}))
        }

    }

    //this fucntion will gather username and password
    //and send a POST to our java server
    const login = async () => {

        //TODO: We could (and should) validate user input here as well as backend

        //Send a POST request to the backend for login
        //NOTE: with credentials is what lets us save/send user session info
        const response = await axios.post("http://localhost:8080/users/login",
        user,
        {withCredentials:true}) //this is important for cookies to be stored    
        .then((response) => {

            //if the login was successful, log the user in and store their info in global state
            state.userSessionData = response.data

            console.log(state.user)

            alert("Welcome, " + state.userSessionData.username + "!")

            //use our useNavigate hook to switch views to the Employee or Manager landing page based on role
            if(state.userSessionData.role === "Employee"){
                //alert("Inside Employee redirect!")
                navigate("/employee")
            }
            else if(state.userSessionData.role === "Manager"){
                //alert("Inside Manager redirect!")
                navigate("/manager")
            }
            else{
                alert("Redirect failed!")
            }

        })
        .catch((error) => {

            //if the login was unsuccessful, alert the user
            alert("Login Failed! Please try again.")
        })


        
    }


    return(
        <div className="login">
            <div className="text-container">
                <h1>Employee Reimbursement System</h1>
                <h3>Please sign in to access records.</h3>

                <div className="input-container">
                    <input type="text" placeholder="username" name="username" onChange={storeValues}/>
                </div>

                <div className="input-container">
                    <input type="password" placeholder="password" name="password" onChange={storeValues}/>
                </div>

                <div>
                    <button className="login-button" onClick={login}>Login</button>
                    <button className="login-button" onClick={() => navigate("/register")}>Create Account</button>
                </div>
            </div>



            {/* Conditional Rendering to display last caught pokemon from global storage 
            
            {state.lastCaughtPokemon.image ? <div>
                <h3>Last Caught Pokemon:</h3>
                <img src={state.lastCaughtPokemon.image} alt="POKEMON PIC" />
            </div> : <h3>You haven't caught a Pokemon recently.</h3>}
            
            
            */}
            
            

        </div>


    )
}