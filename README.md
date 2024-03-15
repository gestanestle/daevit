# **daevit**

_Navigate to `daevit-backend` and/or to `daevit-frontend` for the server and/or frontend-specific documentation of this project, respectively._

### **How to run in development mode**

1. Go to clerk.com and create a new application.
2. Copy the API Keys in Developers section to .env.local of the Next.js app.
3. Don't forget to turn on webhook events with the domain name of your hosted backend, then append the route `api/v1/user` to sync sign-ups from frontend to backend.
   Ex: `http://enhanced-potassium.app/api/1/user`
4. Define the environment variable `SERVER_HOST` in .env.local as well, which is essentially the hostname of the backend app.
5. Run the containers with `docker compose up -d`.
