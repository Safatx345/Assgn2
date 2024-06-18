// src/App.js
import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import CreateResume from './components/createResume';
import GetResume from './components/GetResume';

const App = () => {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/student/createresume" element={<CreateResume/>} />
          <Route path="/student/getresume/:role" element={<GetResume />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;
