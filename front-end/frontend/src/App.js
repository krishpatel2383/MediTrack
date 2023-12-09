// Import statements (Ensure accurate paths and correct component naming)
import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Home from "./components/home"; // Component name should start with an uppercase letter
import { Login } from "./components/Login";

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />{" "}
        {/* Use "element" prop to render the component */}
        <Route path="/login" element={<Login />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
