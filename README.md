<h1>JSW Social Media Application</h1>

<h3>Features</h3>

<h5>USER -CONTROLLER</h5>

<li>Create User  - API (POST) ENDPOINT - http://localhost:9192/api/user/signup</li>
<br>
Request body
<br>

```json

{
"email": "faiz@athar.com",
"password": "faiz",
"username": "faiz"
}

```
Response
```json
{
  "successMessages": [
    "User created successfully"
  ],
  "errorMessages": [],
  "o": {
    "username": "faiz",
    "password": null,
    "email": "faiz@athar.com"
  }
}
```

<li>Login - API (POST) ENDPOINT - http://localhost:9192/api/user/signin </li>
<br>
Request body
<br>

```json
{
  "password": "faiz123",
  "userName": "faiz"
}
```

Response

```json
{
  "successMessages": [
    "User authenticated successfully"
  ],
  "errorMessages": null,
  "o": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmYWl6IiwiZXhwIjoxNjcwODA1MDM4LCJpYXQiOjE2NzA3NjkwMzh9.zzti70g60u4vB9iLjW_ScDIOu6LyZKrd7M2UXdk3a6o"
}
```

<h5>CREATE POST CONTROLLER</h5>

<li>Upload image API (POST) ENDPOINT - http://localhost:9192/api/post/upload/image</li>
<li>Get uploaded images - API (GET) ENDPOINT - http://localhost:9192/api/post/upload/image</li>

<h5>FOLLOW CONTROLLER</h5>

<li>Follow user  - API (PUT) ENDPOINT - http://localhost:9192/api/follow</li>
<br>
Request Param
<br>

```url
http://localhost:9192/api/follow?username=faiz1
```

```json
{
  "successMessages": [
    "You have successfully followed faiz1"
  ],
  "errorMessages": null,
  "o": [
    {
      "username": "faiz1",
      "password": null,
      "email": "sb1@sb.com"
    },
    {
      "username": "faiz2",
      "password": null,
      "email": "sb2@sb.com"
    }
  ]
}
```


<li>Get Followers  - API (GET) ENDPOINT - http://localhost:9192/api/followers</li>
<li>Get Followings - API (GET) ENDPOINT - http://localhost:9192/api/followings</li>
<li>Second level connections || suggestions - API (GET) ENDPOINT - http://localhost:9192/api/suggestions</li>

<h4>Authentication With JWT<h4> Token patternt - Bearer {token}
<br>
Example 
<br>

```text
Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmYWl6IiwiZXhwIjoxNjcwODAzMDU1LCJpYXQiOjE2NzA3NjcwNTV9.mCsIbFMnwCcg7ByiDvSWIVwxch-aEZ83_Uf7AOorj8c
```

<a href="http://localhost:9192/swagger-ui.html">Swagger URL</a>

