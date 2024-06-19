// src/components/GetResume.js
import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import '../css/GetResume.css';

const GetResume = () => {
  const { role } = useParams();
  const [resume, setResume] = useState(null);

  useEffect(() => {
    const fetchResume = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/api/resume/get/${role}`);
        console.log(response);
        setResume(response.data);
      } catch (error) {
        console.error('Error fetching resume', error);
      }
    };

    fetchResume();
  }, [role]);

  return (
    <div className="container">
      <h2 className="title">{role.charAt(0).toUpperCase() + role.slice(1)} Resumes</h2>
      {resume ? (
        resume.map((res, index) => (
          <div key={index} className="resume">
            <p><strong>Name:</strong> {res.firstName + " " + res.lastName}</p>
            <p><strong>Email:</strong> {res.email}</p>
            {/* <p><strong>Experience:</strong></p> */}

            {role === 'developer' &&  (
                <div>
                    <p><strong> Qualifications: </strong> {res.qualifications} <br /></p>
                    <p><strong> Tech Skills: </strong> {res.techSkills} <br /></p>
                    <p><strong> Business Impact: </strong> {res.businessImpact}</p>
                </div>
            )}
            {role === 'tutor' && (
                <div>
                    <strong> Courses Taken: </strong> {res.coursesTaught} <br />
                    <strong> YoE: </strong> {res.yearsOfExperience}
                </div>
            )}
          </div>
        ))
      ) : (
        <p>Loading...</p>
      )}
    </div>
  );
};

export default GetResume;
