<h1>JSW Social Media Application</h1>

<h3>Features</h3>

<h5>USER -CONTROLLER</h5>

<li>Create User  - API (POST) ENDPOINT - http://localhost:9192/api/user/signup</li>
<br>
Request body
<br>

```

{
"email": "faiz@athar.com",
"password": "faiz123",
"username": "faiz"
}

```
Response
```json
{
  "username": "faiz",
  "password": null,
  "email": "faiz@athar.com"
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

```text
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJmYWl6IiwiZXhwIjoxNjcwODAzMDU1LCJpYXQiOjE2NzA3NjcwNTV9.mCsIbFMnwCcg7ByiDvSWIVwxch-aEZ83_Uf7AOorj8c
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
