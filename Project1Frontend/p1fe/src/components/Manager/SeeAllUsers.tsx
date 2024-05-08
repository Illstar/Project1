import { useEffect, useState } from "react"
import { UserInterface } from "../interfaces/UserInterface"
import { User } from "../User/User"
import axios from "axios"
import "./Manager.css"
import { useNavigate } from "react-router-dom"

export const SeeAllUsers: React.FC = () => {

    const [user, setUser] = useState<UserInterface[]>([])

    //I want to get all Users when the component renders,
    //so we'll use useEffect
    useEffect(() => {
        getAllUsers()
    }, []) //empty array so this triggers on component load and state change

    //We need a useNavigate hook to allow use to nagivate between compoennts
    //No more manual URL changes!
    const navigate = useNavigate()

    //GET request to server to get all Users
    const getAllUsers = async () => {

        //our GET request (remember to send withCredentials to confirm user is logged in)
        const response = await axios.get("http://localhost:8080/users", {withCredentials:true})

        //populate the User state with the incoming data
        setUser(response.data)

        console.log(response.data)

    }


    return (
        <div className="see-all-users">
            <div className="text-container">
                <h1>See All Users</h1>

                <div className="user-container">
                    {/*Using map(), for every User
                    display one User component. */}
                    {user.map((u, index) => {
                        return(
                            <div key={index}>
                                <User {...u}></User>                        
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