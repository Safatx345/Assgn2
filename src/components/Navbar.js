import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import '../css/Navbar.css';

const Navbar = () => {
  const [dropdownVisible, setDropdownVisible] = useState(false);

  return (
    <nav className="navbar">
      <ul>
        <li><Link to="/">Home</Link></li>
        <li><Link to="/student/createresume">Create Resume</Link></li>
        <li
          onMouseEnter={() => setDropdownVisible(true)}
          onMouseLeave={() => setDropdownVisible(false)}
        >
          <button>
            Roles
          </button>
          {dropdownVisible && (
            <div className="dropdown-content">
              <Link to="/student/getresume/developer">Developer</Link>
              <Link to="/student/getresume/tutor">Tutor</Link>
            </div>
          )}
        </li>
      </ul>
    </nav>
  );
};

export default Navbar;
