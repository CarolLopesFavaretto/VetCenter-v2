db.createUser(
        {
            user: "vet",
            pwd: "center",
            roles: [
                {
                    role: "readWrite",
                    db: "db_vetcenter"
                }
            ]
        }
);