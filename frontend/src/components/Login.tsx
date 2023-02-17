import axios from "axios";
import { FormEvent, SetStateAction, useState } from "react";
import API from "../config/ApiConfig";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  function updateUsername(event: { target: { value: SetStateAction<string>; }; }) {
    setUsername(event.target.value);
  }

  function updatePassword(event: { target: { value: SetStateAction<string>; }; }) {
    setPassword(event.target.value);
  }

  function submit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    if (username && password) {
      API.post('login', {
        name: username,
        password: password
      })
      .then((response) => {
        sessionStorage.setItem('token', response.headers.authorization)
        axios.defaults.headers.common.Authorization = response.headers.authorization
        window.location.href = '/'
      })
      .catch((response) => alert('Error'))
    }
  }

  return (
    <>
      <form onSubmit={submit}>
        <input type="text" name="username" id="username" data-ddg-inputtype="credentials.username" value={username} placeholder="username" onChange={updateUsername} /><br />
        <input type="password" name="password" id="password" data-ddg-inputtype="credentials.password" value={password} placeholder="password" onChange={updatePassword} /><br />
        <input type="submit" value="Login" />
      </form><br />
      <a href='signup'>Click here to signup</a>
    </>
  );
}

export default Login;