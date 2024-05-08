import { useEffect, useState } from "react"
import "./Manager.css"
import { ReimbursementInterface } from "../interfaces/ReimbursementInterface"
import axios from "axios"
import { Reimbursement } from "../Reimbursement/Reimbursement"
import { useNavigate } from "react-router-dom"

export const SeeAllReimbursements: React.FC=()=> {

    const [reimbursement, setReimbursement] = useState<ReimbursementInterface[]>([])

    //I want to get all pending Reimbursements when the component renders,
    //so we'll use useEffect
    useEffect(() => {
        getAllReimbursements()
    }, []) //empty array so this triggers on component load and state change

    //We need a useNavigate hook to allow use to nagivate between compoennts
    //No more manual URL changes!
    const navigate = useNavigate()

    //GET request to server to get all Reimbursements
    const getAllReimbursements = async () => {

        //our GET request (remember to send withCredentials to confirm user is logged in)
        const response = await axios.get("http://localhost:8080/reimbursements/boss", {withCredentials:true})

        //populate the Reimbursement state with the incoming data
        setReimbursement(response.data)

        console.log(response.data)

    }


    return (
        <div className="see-all-reimbursements">
            <div className="text-container">
                <h1>See All Reimbursements</h1>

                <div className="reimbursement-container">
                    {/*Using map(), for every Reimbursement that belongs to the logged in user,
                    display one Reimbursement component. */}
                    {reimbursement.map((reimb, index) => {
                        return(
                            <div key={index}>
                                <Reimbursement {...reimb}></Reimbursement>                        
                            </div>
                        )
                    })}

                    {/*If you need to render multiple things in map(), they need
                    to be in a <div> */}
                </div>

                <div>
                    
                    <button className="login-button" onClick={() => navigate("/manager")}>Back</button>
                </div>
            </div>
        </div>
    );
}