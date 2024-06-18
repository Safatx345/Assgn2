import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import CreateResume from './components/createResume';
import GetResume from './components/GetResume';
import Navbar from './components/Navbar';
import Homepage from './components/Homepage';

const App = () => {
  return (
    <Router>
      <div>
        <Navbar />
        <Routes>
        <Route path="/" element={<Homepage />} />
          <Route path="/student/createresume" element={<CreateResume />} />
          <Route path="/student/getresume/:role" element={<GetResume />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
