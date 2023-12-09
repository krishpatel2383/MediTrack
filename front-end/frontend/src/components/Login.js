import React from "react";
import "../css/Login.css";

export const Login = () => {
  return (
    <div className="login">
      <div className="div">
        <div className="overlap">
          <img
            className="ic-baseline-email"
            alt="Ic baseline email"
            src="ic-baseline-email.svg"
          />
          <div className="rectangle" />
          <div className="text-wrapper">Email</div>
        </div>
        <img
          className="img"
          alt="Img"
          src="e3721ddd6a2db50936b22410a0d344ac-1.svg"
        />
        <div className="overlap-group">
          <div className="text-wrapper-2">Welcome Back!</div>
          <div className="text-wrapper-3">Login to continue</div>
        </div>
        <div className="overlap-2">
          <div className="text-wrapper-4">Login As</div>
          <img className="vector" alt="Vector" src="vector.svg" />
          <img
            className="gridicons-dropdown"
            alt="Gridicons dropdown"
            src="gridicons-dropdown.svg"
          />
        </div>
        <div className="overlap-group-2">
          <div className="text-wrapper-5">Password</div>
          <img className="vector-2" alt="Vector" src="image.svg" />
        </div>
        <div className="div-wrapper">
          <div className="text-wrapper-6">Login</div>
        </div>
        <div className="text-wrapper-7">Forget Password?</div>
        <p className="new-user-sign-up">
          <span className="span">New User?&nbsp;&nbsp;</span>
          <span className="text-wrapper-8">Sign Up</span>
        </p>
        <p className="MEDITRACK">
          <span className="text-wrapper-8">MEDI</span>
          <span className="text-wrapper-9">TRACK</span>
        </p>
        <img className="OBJECTS" alt="Objects" src="OBJECTS.png" />
      </div>
    </div>
  );
};
