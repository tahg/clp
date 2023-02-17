import { FormEvent, SetStateAction, useState } from "react";
import API from "../config/ApiConfig";

function Signup() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [password2, setPassword2] = useState("");
  const [error, setError] = useState("");
  const [valid, setValid] = useState(false);

  function updateUsername(event: { target: { value: SetStateAction<string>; }; }) {
    setUsername(event.target.value);
    validate(event);
  }

  function updatePassword(event: { target: { value: SetStateAction<string>; }; }) {
    setPassword(event.target.value);
    validate(event);
  }

  function updatePassword2(event: { target: { value: SetStateAction<string>; }; }) {
    setPassword2(event.target.value);
    setValid(false);
    setError("");
    validate(event);
  }

  function validate(event: { target: { value: SetStateAction<string>; }; }) {
    if (!username || !password) {
      setError("Please enter a username and password");
      setValid(false);
    }
    else if (password === password2) {
      setError("");
      setValid(true);
    }
    else {
      setError("Passwords must match");
      setValid(false);
    }
  }

  function submit(event: FormEvent<HTMLFormElement>) {
    event.preventDefault();
    API.post('signup', {
      name: username,
      password: password
    })
    .then(() => window.location.href = '/login')
    .catch(() => alert('Error'))  
  }

  return (
    <form onSubmit={submit}>
      <input type="text" name="username" id="username" data-ddg-inputtype="credentials.username" value={username} placeholder="username" onChange={updateUsername} /><br />
      <input type="password" name="password" id="password" data-ddg-inputtype="credentials.password" value={password} placeholder="password" onChange={updatePassword} /><br />
      <input type="password" name="password_confirm" id="password_confirm" data-ddg-inputtype="credentials.password" value={password2} placeholder="password again" onChange={updatePassword2} onBlur={validate} /><br />
      <div>{error}</div>
      <input type="submit" value="Signup" disabled={!valid}/>
    </form>
  );
}

export default Signup;