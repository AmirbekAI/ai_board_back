This is a back end for a AI powered sticky notes board.

It might take a little bit long in order to sign up and login for the first time due to the server limitations

The base url for the back end is: https://ai-board-back.onrender.com

Endpoints: 

POST api/auth/register
- Register a new user
- Body: { "email": "string", "password": "string" }

POST api/auth/login
- Login user
- Body: { "email": "string", "password": "string" }

GET /api/boards
- Get all boards for authenticated user
- Header: Authorization: Bearer {token}

POST /api/boards
- Create new board
- Header: Authorization: Bearer {token}
- Body: { "title": "string" }

PUT /api/boards/{id}
- Update board title
- Header: Authorization: Bearer {token}
- Body: { "title": "string" }

DELETE /api/boards/{id}
- Delete board
- Header: Authorization: Bearer {token}

GET /api/boards/{boardId}/notes
- Get all notes for a board
- Header: Authorization: Bearer {token}

POST /api/boards/{boardId}/notes
- Create new note
- Header: Authorization: Bearer {token}
- Body: { "content": "string", "x": number, "y": number }

PUT /api/boards/{boardId}/notes/{noteId}
- Update note
- Header: Authorization: Bearer {token}
- Body: { "content": "string", "x": number, "y": number }

DELETE /api/boards/{boardId}/notes/{noteId}
- Delete note
- Header: Authorization: Bearer {token}

GET /api/boards/{boardId}/edges
- Get all edges for a board
- Header: Authorization: Bearer {token}

POST /api/boards/{boardId}/edges
- Create new edge
- Header: Authorization: Bearer {token}
- Body: { 
    "sourceNoteId": "long",
    "targetNoteId": "long",
    "type": "string",
    "style": "json object"
}

DELETE /api/boards/{boardId}/edges/{edgeId}
- Delete edge
- Header: Authorization: Bearer {token}
