import React from "react";
import "../css/home.css";

const home = () => {
  console.log("Rendering the home component");
  return (
    <div className="desktop">
      <div className="div">
        <p className="MEDITRACK">
          <span className="text-wrapper">MEDI</span>
          <span className="span">TRACK</span>
        </p>
        <img
          className="OBJECTS"
          alt="Objects"
          src="https://c.animaapp.com/amRd3zMw/img/objects@2x.png"
        />
        <a href="/login">
          <div className="overlap">
            <div className="text-wrapper-2">Sign in</div>
          </div>
        </a>
        <div className="about-us">About&nbsp;&nbsp;us</div>
        <div className="group">
          <div className="overlap-group">
            <div className="text-wrapper-3">Sign up for free</div>
            <img
              className="img"
              alt="Group"
              src="https://c.animaapp.com/amRd3zMw/img/group-2@2x.png"
            />
          </div>
        </div>
        <div className="overlap-2">
          <img
            className="element"
            alt="Element"
            src="https://c.animaapp.com/amRd3zMw/img/6974922-4447-1.svg"
          />
          <p className="p">
            Addressing the Health Management Challenges in the Digital Age.
          </p>
        </div>
      </div>
    </div>
  );
};

export default home;
