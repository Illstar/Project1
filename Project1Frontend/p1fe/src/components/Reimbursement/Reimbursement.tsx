
import { ReimbursementInterface } from "../interfaces/ReimbursementInterface";
import "./Reimbursement.css"

export const Reimbursement: React.FC<ReimbursementInterface> = (reimbursement:ReimbursementInterface) => {

    return (
        <div className="reimbursement-container">
            <div>
                <h3>Reimbursement ID: {reimbursement.reimbursementId}</h3>
                <h3>Amount: {reimbursement.amount}</h3>
                <h3>Status: {reimbursement.status}</h3>
                <h3>Description: {reimbursement.description}</h3>
            </div>
        </div>
    );
}