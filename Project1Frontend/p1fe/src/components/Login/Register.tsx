
import { useState } from "react"
import { UserInterface } from "../interfaces/UserInterface"
import { useNavigate } from "react-router-dom"
import axios from "axios"

export const Register: React.FC = () => {

    //set state (UserInterface)
    const[user, setUser] = useState<UserInterface>({
        username:"",
        password:"",
        firstName: "",
        lastName: "",
        role: "Employee" //default role is Employee
    })

    //useNavigate to navigate between components
    const navigate = useNavigate()


    //function to store input box values
    const storeValues = (input:any) => {

        //if the input that has changed is the "username" input, change the value of username in the user state object

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

    }


    //function to send a POST with user data to register a user in the backend
    //! Remember, requests to our Java server will only work with @CrossOrigin in our Controllers
    const register = async () => {

        //This request goes to our backend
        const response = await axios.post("http://localhost:8080/users", user)

        alert(response.data) //"{user} was created!"

        //after registration, send the user back to login page
        navigate("/")

    }


    return(
        <div className="login">

            <div className="text-container">
                <h1>New here? Create an Account for free!</h1>

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
                
                {/*Testing Radio Button for Role Select */}
                {/*
                <div className="input-radio-container">
                    <h3>Please select your role:</h3>
                    
                    <div className="radio-container">
                    <input type="radio" defaultChecked value="Employee" id="employee" name="role" onChange={storeValues}/>
                    <label htmlFor="employee">Employee</label>
                    <p></p>
                    <input type="radio" value="Manager" id="manager" name="role" onChange={storeValues}/>
                    <label htmlFor="manager">Manager</label>
                    </div>
                    
                    
                </div>
                */}

                <div>
                    <button className="login-button" onClick={register}>Submit</button>
                    <button className="login-button" onClick={() => navigate("/")}>Back</button>
                </div>
                

            </div>

        </div>
    )
}