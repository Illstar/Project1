import { UserInterface } from "../interfaces/UserInterface";
import "./User.css"

export const User: React.FC<UserInterface> = (user:UserInterface) => {

    return (
        <div className="user-container">
            <div>
                <h3>User ID: {user.userId}</h3>
                <h3>Username: {user.username}</h3>
                <h3>First Name: {user.firstName}</h3>
                <h3>Last Name: {user.lastName}</h3>
                <h3>Role: {user.role}</h3>
            </div>
        </div>
    );
}