import { useState } from "react"
import "./Manager.css"
import { UserInterface } from "../interfaces/UserInterface"
import { useNavigate } from "react-router-dom"
import axios from "axios"
import { error } from "console"

export const DeleteAUser: React.FC = () => {

    //A variable to store user input for finding a Reimbursement
    //TODO: modify
    const [userInput, setUserInput] = useState<number>(0)

    //we need to store User state to use when rendering the UserComponent
    const [user, setUser] = useState<UserInterface>({
        userId:0,
        username:""
    })

    //We need a useNavigate hook to allow use to nagivate between compoennts
    //No more manual URL changes!
    const navigate = useNavigate()

    //a function that stores the user input (Which we need for our DEL request)
    const gatherInput = (input:any) => {
        setUserInput(input.target.value) //set the userInput to what's in the input box
    }
    
    //function to store input box values
    const storeValues = (input:any) => {

        if(input.target.name === "userid"){
            setUser((user) => ({...user, userId:input.target.value}))
        } 
        //if the input that has changed is the "username" input, change the value of username in the user state object
        /*
        if(input.target.name === "username"){
            setUser((user) => ({...user, username:input.target.value}))
        } else if(input.target.name === "password"){
            setUser((user) => ({...user, password:input.target.value}))
        } else if(input.target.name === "firstname"){
            setUser((user) => ({...user, firstName:input.target.value}))
        } else if(input.target.name === "lastname"){
            setUser((user) => ({...user, lastName:input.target.value}))
        } else if(input.target.name === "role"){
            setUser((user) => ({...user, role:input.target.value}))
        }
        */
    
    }

    //function to send a DELETE with input data to delete a user by userId
    //! Remember, requests to our Java server will only work with @CrossOrigin in our Controllers
    const deleteAUser = async ()  => {

        

        //This request goes to our backend
        const response = await axios.delete("http://localhost:8080/users/" + user.userId,
        {withCredentials:true})
        .then((response)=>alert(response.data)) //"{user} was deleted!"
        .then((response)=>navigate("/manager")) //after deletion, send the user back to manager landing page
        .catch((error) => {

            //if the login was unsuccessful, alert the user
            alert(error.response.data)
        })

        

        

        
        

    }

    return(

        <div className="delete-a-user">
            
            <div className="text-container">
                <h1>Please enter the User ID to be deleted.</h1>
                
                <div className="input-container">
                    <input type="number" min="0" step="1" pattern="[0-9]" placeholder="User ID" name="userid" onChange={storeValues}/>
                </div>
               
                {/*
                <div className="input-container">
                    <input type="text" placeholder="username" name="username" onChange={storeValues}/>
                </div>
                <div className="input-container">
                    <input type="password" placeholder="password" name="password" onChange={storeValues}/>
                </div>
                <div className="input-container">
                    <input type="text" placeholder="First Name" name="firstname" onChange={storeValues}/>
                </div>
                <div className="input-container">
                    <input type="text" placeholder="Last Name" name="lastname" onChange={storeValues}/>
                </div>
                */}

                {/*Testing Radio Button for Role Select */}
                {/*
                <div className="input-radio-container">
                    <h3>Please select your role:</h3>
                    
                    <div className="radio-container">
                    <input type="radio" value="Employee" id="employee" name="role" onChange={storeValues}/>
                    <label htmlFor="employee">Employee</label>
                    <p></p>
                    <input type="radio" value="Manager" id="manager" name="role" onChange={storeValues}/>
                    <label htmlFor="manager">Manager</label>
                    </div>
                </div>

                */}

                
                <div>
                    <button className="login-button" onClick={deleteAUser}>Delete</button>
                    <button className="login-button" onClick={() => navigate("/manager")}>Back</button>
                </div>
                

            </div>
        </div>

    )

}

