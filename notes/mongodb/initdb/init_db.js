let db = connect("mongodb://root:rootroot@127.0.0.1:27017/abernathy?authSource=admin");

db = db.getSiblingDB('abernathy');

db.createUser(
    {
        user: "user1",
        pwd: "userPass",
        roles: [
            {
                role: "readWrite",
                db: "abernathy"
            }
        ]
    }
);

db.createCollection("notes");

db.notes.insert({
    patientId: 1,
    fullName: "TestNone",
    note: "Patient states that they are 'feeling terrific' Weight at or below recommended level"
});