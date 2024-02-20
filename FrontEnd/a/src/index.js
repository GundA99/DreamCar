import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import "bootstrap/dist/css/bootstrap.min.css";
import "remixicon/fonts/remixicon.css";
import "slick-carousel/slick/slick.css";
import "slick-carousel/slick/slick-theme.css";

import { BrowserRouter as Router } from "react-router-dom";// Importing React Router for routing

// Create a root instance using ReactDOM.createRoot and specify the DOM element to render the app into

const root = ReactDOM.createRoot(document.getElementById("root"));
// Render the app within a React Strict Mode and wrap it with a Router component for routing
root.render(
  <React.StrictMode>
    <Router>
      <App />
    </Router>
  </React.StrictMode>
);
