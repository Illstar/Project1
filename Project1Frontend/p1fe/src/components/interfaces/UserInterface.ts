//Just like a Java Model Class, our React Interafaces
//typically MODEL some data/data type we intend to use.
export interface UserInterface {
    userId?:number,
    username: string,
    password?: string,
    firstName?: string,
    lastName?: string,
    role?: string
}