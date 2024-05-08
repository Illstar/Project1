import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { Login } from './components/Login/Login';
import { Employee } from './components/Employee/Employee';
import { Register } from './components/Login/Register';
import { Manager } from './components/Manager/Manager';
import { CreateNewReimbursement } from './components/Employee/CreateNewReimbursement';
import { SeeMyReimbursements } from './components/Employee/SeeMyReimbursements';
import { SeeMyPendingReimbursements } from './components/Employee/SeeMyPendingReimbursements';
import { SeeAllReimbursements } from './components/Manager/SeeAllReimbursements';
import { SeeAllPendingReimbursements } from './components/Manager/SeeAllPendingReimbursements';
import { ResolveAReimbursement } from './components/Manager/ResolveAReimbursement';
import { SeeAllUsers } from './components/Manager/SeeAllUsers';
import { DeleteAUser } from './components/Manager/DeleteAUser';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="" element={<Login/>}/>
          <Route path="/register" element={<Register/>}/>
          <Route path="/employee" element={<Employee/>}/>
          <Route path="/manager" element={<Manager/>}/>
          <Route path="/new_r" element={<CreateNewReimbursement/>}/>
          <Route path="/my_r" element={<SeeMyReimbursements/>}/>
          <Route path="/my_p_r" element={<SeeMyPendingReimbursements/>}/>
          <Route path="/all_r" element={<SeeAllReimbursements/>}/>
          <Route path="/all_p_r" element={<SeeAllPendingReimbursements/>}/>
          <Route path="/resolve_r" element={<ResolveAReimbursement/>}/>
          <Route path="/all_users" element={<SeeAllUsers/>}/>
          <Route path="/delete_user" element={<DeleteAUser/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
