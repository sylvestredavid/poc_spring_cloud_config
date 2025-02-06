import logo from './logo.svg';
import './App.css';
import {Suspense, use, useEffect, useState} from "react";
import axios from "axios";
import {useForm} from "react-hook-form";

function App() {

  const { register, handleSubmit, reset } = useForm();

  useEffect(() => {
    axios.get("http://localhost:8888/config").then(res => reset(res.data))
  }, [reset])


  const onSubmit = (data) => {
    axios.put("http://localhost:8888/config", data).then(() => console.log("ok"));
  };

  return (
    <div className="App">
      <form onSubmit={handleSubmit(onSubmit)}>
        <label>App Name:</label>
        <input {...register("app")} />

        <label>User Name:</label>
        <input {...register("user.username")} />

        <label>User Role:</label>
        <input {...register("user.role")} />

        <label>Admin URI:</label>
        <input {...register("urls.admin.uri")} />

        <label>Admin activé:</label>
        <input type="checkbox" {...register("urls.admin.enabled")} />

        <label>Back URI:</label>
        <input {...register("urls.back.uri")} />

        <label>Back activé:</label>
        <input type="checkbox" {...register("urls.back.enabled")} />

        <button type="submit">Submit</button>
      </form>
    </div>
  );
}

export default App;
