import React, { useState } from "react";
import { useAppContext } from "../lib/contextLib";
import { useNavigate } from "react-router-dom";
import "./Login.css";

export default function Login() {
  const nav = useNavigate();

  // React States
  const [errorMessages, setErrorMessages] = useState({});
  const [isSubmitted, setIsSubmitted] = useState(false);
  const { userHasAuthenticated } = useAppContext();
  const { userType } = useAppContext();

  // User Login info
  const database = [
    {
      type: "student",
      username: "student",
      password: "pass"
    },
    {
      type: "admin",
      username: "admin",
      password: "pass"
    }
  ];

  const errors = {
    uname: "invalid username",
    pass: "invalid password"
  };

  const handleSubmit = (event) => {
      //Prevent page reload
      event.preventDefault();

      var { uname, pass } = document.forms[0];

      // Find user login info
      const userData = database.find((user) => user.username === uname.value);

      // Compare user info
      if (userData) {
        if (userData.password !== pass.value) {
          // Invalid password
          setErrorMessages({ name: "pass", message: errors.pass });
        } else {
          setIsSubmitted(true);
          userHasAuthenticated(true)
          if (userData.type === "student") {
            userType(true)
          } else if (userData.type === "admin") {
            userType(false)
          }
          nav("/react-spring-project/home");
        }
      } else {
        // Username not found
        setErrorMessages({ name: "uname", message: errors.uname });
      }
  };

  // Generate JSX code for error message
  const renderErrorMessage = (name) =>
    name === errorMessages.name && (
      <div className="error">{errorMessages.message}</div>
  );

  // JSX code for login form
  const renderForm = (
    <div className="form">
      <form onSubmit={handleSubmit}>
        <div className="input-container">
          <label>Username </label>
          <input type="text" name="uname" required />
          {renderErrorMessage("uname")}
        </div>
        <div className="input-container">
          <label>Password </label>
          <input type="password" name="pass" required />
          {renderErrorMessage("pass")}
        </div>
        <div className="button-container">
          <input type="submit" />
        </div>
      </form>
    </div>
  );

  return (
    <div className="app">
      <div className="login-form">
        <div className="title">Welcome!</div>
        {isSubmitted ? <div>User is successfully logged in</div> : renderForm}
      </div>
    </div>
  );
}