//import { PokemonInterface } from "../interfaces/PokemonInterface";
import { UserInterface } from "../interfaces/UserInterface";

/*This is a rudimentary implementation of a store, which is
basically global data storage.  Any data you want to store
globally (visibile to the entire app) can reside here.
Look into the context API for a more robust solution.*/
export const state:any = {

    //we typically want to store user session info on the front end
    //for personalization as well as role-based security control
    userSessionData: {
        userId:0,
        username:"",
        firstName:"",
        lastName:"",
        role:"" // would be used to deteremine if a user can do certain things
        //role:"" // would be used to deteremine if a user can do certain things

    } as UserInterface,

    //storing the last caught pokemon for global display
    /*
    lastCaughtPokemon: {
        name:"",
        image:""
    } as PokemonInterface,
    */
    //Think about your requirements when storing state globally
    //you only NEED to globally store data you intend ot use in multiple components
    //but you could optimize your code by using global storage to
    //reduce calls to your server/database

    //we could also store things like base URLs for our API calls
    baseUrl:"http://localhost:8080",
    //basePokemonUrl:"https://pokeapi.co/api/v2/pokemon"

    //TODO: store our incoming JWT
}

//Side note: typically all global user data will get populated upon successful login
//Other side note: There's no built in getter/setter mechanisms, which would be nice for encapsulation


//For a solution like this, we should probably use local/session storage
//for those getters/setters as well as data that persists on page reload