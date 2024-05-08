import { useNavigate } from "react-router-dom"
import "./Manager.css"
import { useState } from "react"
import { ReimbursementInterface } from "../interfaces/ReimbursementInterface"
import axios from "axios"

export const ResolveAReimbursement: React.FC = () => {

    //A variable to store user input for finding a Reimbursement
    //TODO: modify
    const [userInput, setUserInput] = useState<number>(0)

    //we need to store Reimbursement state to use when rendering the ReimbursementComponent
    const [reimbursement, setReimbursement] = useState<ReimbursementInterface>({
        amount:0,
        status:"APPROVED",
        description:""
    })

    //We need a useNavigate hook to allow use to nagivate between compoennts
    //No more manual URL changes!
    const navigate = useNavigate()

    //a function that stores the user input (Which we need for our GET request)
    const gatherInput = (input:any) => {
        setUserInput(input.target.value) //set the userInput to what's in the input box
    }
    
    //function to store input box values
    const storeValues = (input:any) => {

        
        //if the input that has changed is the "username" input, change the value of username in the user state object
      

        if(input.target.name === "reimbursementid"){
            setReimbursement((reimbursement) => ({...reimbursement, reimbursementId:input.target.value}))
        } else if (input.target.name === "status"){
            setReimbursement((reimbursement) => ({...reimbursement, status:input.target.value}))
        }
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
    
    //function to send a POST with input data to create a new reimbursement in the backend
    //! Remember, requests to our Java server will only work with @CrossOrigin in our Controllers
    const resolveAReimbursement = async () => {

        //This request goes to our backend
        const response = await axios.put("http://localhost:8080/reimbursements/" + reimbursement.reimbursementId,
        reimbursement, 
        {withCredentials:true})
        .then((response)=>alert(response.data)) //"{reimbursement} was created!"
        .then((response)=>navigate("/manager")) //after deletion, send the user back to employee landing page
        .catch((error) => {

            //if the login was unsuccessful, alert the user
            alert(error.response.data)
        })


    }
    
    


    return (
        <div className="resolve-a-reimbursement">
            
            <div className="text-container">
                <h1>Resolve A Reimbursement</h1>
                
                
                
                <div className="input-container">
                    <input type="number" min="1" step="1" pattern="[0-9]" placeholder="Reimbursement ID" name="reimbursementid" onChange={storeValues}/>
                </div>


                {/*
                <div className="input-container">
                    <input type="text" placeholder="Description" name="description" onChange={storeValues}/>
                </div>



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

                {/*Testing Radio Button for Status Select */}
                <div className="input-radio-container">
                    <h3>Please select the new status:</h3>
                    
                    <div className="radio-container">
                    <input type="radio" defaultChecked value="APPROVED" id="approved" name="status" onChange={storeValues}/>
                    <label htmlFor="approved">APPROVED</label>
                    <p></p>
                    <input type="radio" value="DENIED" id="denied" name="status" onChange={storeValues}/>
                    <label htmlFor="denied">DENIED</label>
                    </div>
                    
                    
                </div>

                <div>
                    <button className="login-button" onClick={resolveAReimbursement}>Submit</button>
                    <button className="login-button" onClick={() => navigate("/manager")}>Back</button>
                </div>

                
                
                

            </div>
        </div>
    );
}