// src/components/CreateResume.js
import React, { useState } from 'react';
import axios from 'axios';

const CreateResume = () => {
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [workExperience, setWorkExperience] = useState('');
    const [qualifications, setQualification] = useState('');
    const [role, setRole] = useState('');
    const [techSkills, setTechSkills] = useState('');
    const [businessImpact, setBusinessImpact] = useState('');
    const [coursesTaught, setCoursesTaken] = useState('');
    const [yearsOfExperience, setYearsExperience] = useState('');
    
    const handleSubmit = async (event) => {
        event.preventDefault();

        if (!firstName || !lastName || !email || !role) {
            alert('Please fill in all required fields');
            return;
          }
        
        if (role === 'developer' && (!techSkills || !businessImpact)) {
        alert('Please fill in all required fields for the developer role');
        return;
        }
    
        if (role === 'tutor' && (!coursesTaught || !yearsOfExperience)) {
        alert('Please fill in all required fields for the tutor role');
        return;
        }
      
        const formData = {
            firstName,
            lastName,
            email,
            workExperience,
            qualifications,
            role,
            techSkills: role === 'developer' ? techSkills : undefined,
            businessImpact: role === 'developer' ? businessImpact : undefined,
            coursesTaught: role === 'tutor' ? coursesTaught : undefined,
            yearsOfExperience: role === 'tutor' ? yearsOfExperience : undefined,
        };

        // console.log(formData);
      
        try {
          const response = await axios.post('http://localhost:8080/api/resume/create', formData);
          console.log(response.data);
          alert('Resume created successfully');
        } catch (error) {
          console.error(error);
          alert('An error occurred. Please try again later');
        }
      };
    


    const handleRoleChange = (e) => {
      setRole(e.target.value);
    };
    
    return (
      <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', height: '100vh', backgroundColor: '#f0f0f0' }}>
        <h2 style={{ marginBottom: '20px' }}>Create Resume</h2>
        <form onSubmit={handleSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '10px', width: '300px' }}>
          {/* ...other fields... */}
          <input type="text" name="firstName" placeholder="First Name" onChange={(e) => setFirstName(e.target.value)} style={{ padding: '10px', borderRadius: '5px', border: '1px solid #ccc' }} />
          <input type="text" name="lastName" placeholder="Last Name" onChange={(e) => setLastName(e.target.value)} style={{ padding: '10px', borderRadius: '5px', border: '1px solid #ccc' }} />
          <input type="text" name="email" placeholder="Email ID" onChange={(e) => setEmail(e.target.value)} style={{ padding: '10px', borderRadius: '5px', border: '1px solid #ccc' }} />
          <input type="text" name="workExperience" placeholder="Work Experience" onChange={(e) => setWorkExperience(e.target.value)} style={{ padding: '10px', borderRadius: '5px', border: '1px solid #ccc' }} />
          <input type="text" name="qualification" placeholder="Qualifaction" onChange={(e) => setQualification(e.target.value)} style={{ padding: '10px', borderRadius: '5px', border: '1px solid #ccc' }} />
          
          <select name="role" onChange={handleRoleChange} style={{ padding: '10px', borderRadius: '5px', border: '1px solid #ccc' }}>
            <option value="">Select Role</option>
            <option value="developer">Developer</option>
            <option value="tutor">Tutor</option>
          </select>
          {role === 'developer' && (
            <>
              <input type="text" name="techSkills" placeholder="Tech Stack" onChange={(e) => setTechSkills(e.target.value)} style={{ padding: '10px', borderRadius: '5px', border: '1px solid #ccc' }} />
              <textarea type="text" name="businessImpact" placeholder="Business Impact" onChange={(e) => setBusinessImpact(e.target.value)} style={{ padding: '10px', borderRadius: '5px', border: '1px solid #ccc' }} />
            </>
          )}
          {role === 'tutor' && (
            <>
              <input type="text" name="coursesTaken" placeholder="Courses Taken" onChange={(e) => setCoursesTaken(e.target.value)} style={{ padding: '10px', borderRadius: '5px', border: '1px solid #ccc' }} />
              <input type="text" name="yearsExperience" placeholder="Years of Experience" onChange={(e) => setYearsExperience(e.target.value)} style={{ padding: '10px', borderRadius: '5px', border: '1px solid #ccc' }} />
            </>
          )}
          <button type="submit" style={{ padding: '10px', borderRadius: '5px', border: 'none', backgroundColor: '#007BFF', color: 'white', cursor: 'pointer' }}>Submit</button>
        </form>
      </div>
    );
    };

export default CreateResume;
