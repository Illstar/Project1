//Just like a Java Model Class, our React Interafaces
//typically MODEL some data/data type we intend to use.
export interface ReimbursementInterface{
    reimbursementId?:number,
    amount?:number,
    status?:string,
    description?:string,
    userId?: number
}