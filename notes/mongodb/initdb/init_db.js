dbAdmin = db.getSiblingDB("admin");

dbAdmin.createUser(
    {
        user: "user1",
        pwd: "userPass",
        roles: [
            {
                role: "dbOwner",
                db: "abernathy"
            }
        ]
    }
);

dbAdmin.auth({
    user: "user1",
    pwd: "userPass"
});

db = new Mongo().getDB("abernathy");
db.createCollection("notes");

db.notes.insert([
    {
        "patient_id": NumberLong(1),
        "full_name": "Lucas Ferguson",
        "note": "Le patient déclare qu'il \u0022 se sent très bien \u0022 \n Poids égal ou inférieur au poids recommandé"
    },
    {
        "patient_id": NumberLong(1),
        "full_name": "Lucas Ferguson",
        "note": "Le patient déclare qu'il se sent fatigué pendant la journée \n Il se plaint également de douleurs musculaires \n Tests de laboratoire indiquant une microalbumine élevée"
    },
    {
        "patient_id": NumberLong(1),
        "full_name": "Lucas Ferguson",
        "note": "Le patient déclare qu'il ne se sent pas si fatigué que ça \n Fumeur, il a arrêté dans les 12 mois précédents \n Tests de laboratoire indiquant que les anticorps sont élevés"
    },
    {
        "patient_id": NumberLong(2),
        "full_name": "Pippa Rees",
        "note": "Le patient déclare qu'il ressent beaucoup de stress au travail \n Il se plaint également que son audition est anormale dernièrement"
    },
    {
        "patient_id": NumberLong(2),
        "full_name": "Pippa Rees",
        "note": "Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois \n Il remarque également que son audition continue d'être anormale"
    },
    {
        "patient_id": NumberLong(2),
        "full_name": "Pippa Rees",
        "note": "Tests de laboratoire indiquant une microalbumine élevée"
    },
    {
        "patient_id": NumberLong(2),
        "full_name": "Pippa Rees",
        "note": "Le patient déclare que tout semble aller bien \n Le laboratoire rapporte que l'hémoglobine A1C dépasse le niveau recommandé \n Le patient déclare qu’il fume depuis longtemps"
    },
    {
        "patient_id": NumberLong(3),
        "full_name": "Edward Arnold",
        "note": "Le patient déclare qu'il fume depuis peu"
    },
    {
        "patient_id": NumberLong(3),
        "full_name": "Edward Arnold",
        "note": "Tests de laboratoire indiquant une microalbumine élevée"
    },
    {
        "patient_id": NumberLong(3),
        "full_name": "Edward Arnold",
        "note": "Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière \n Il se plaint également de crises d’apnée respiratoire anormales \n Tests de laboratoire indiquant un taux de cholestérol LDL élevé"
    },
    {
        "patient_id": NumberLong(3),
        "full_name": "Edward Arnold",
        "note": "Tests de laboratoire indiquant un taux de cholestérol LDL élevé"
    },
    {
        "patient_id": NumberLong(4),
        "full_name": "Anthony Sharp",
        "note": "Le patient déclare qu'il lui est devenu difficile de monter les escaliers \n Il se plaint également d’être essoufflé \n Tests de laboratoire indiquant que les anticorps sont élevés \n Réaction aux médicaments"
    },
    {
        "patient_id": NumberLong(4),
        "full_name": "Anthony Sharp",
        "note": "Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps"
    },
    {
        "patient_id": NumberLong(4),
        "full_name": "Anthony Sharp",
        "note": "Le patient déclare avoir commencé à fumer depuis peu \n Hémoglobine A1C supérieure au niveau recommandé"
    },
    {
        "patient_id": NumberLong(5),
        "full_name": "Wendy Ince",
        "note": "Le patient déclare avoir des douleurs au cou occasionnellement \n Le patient remarque également que certains aliments ont un goût différent \n Réaction apparente aux médicaments"
    },
    {
        "patient_id": NumberLong(5),
        "full_name": "Wendy Ince",
        "note": "Le patient déclare avoir eu plusieurs épisodes de vertige depuis la dernière visite. \n Taille incluse dans la fourchette concernée"
    },
    {
        "patient_id": NumberLong(5),
        "full_name": "Wendy Ince",
        "note": "Le patient déclare qu'il souffre encore de douleurs cervicales occasionnelles \n Tests de laboratoire indiquant une microalbumine élevée \n Fumeur, il a arrêté dans les 12 mois précédents"
    },
    {
        "patient_id": NumberLong(5),
        "full_name": "Wendy Ince",
        "note": "Le patient déclare avoir eu plusieurs épisodes de vertige depuis la dernière visite. \n Tests de laboratoire indiquant que les anticorps sont élevés"
    },
    {
        "patient_id": NumberLong(6),
        "full_name": "Trace Ross",
        "note": "Le patient déclare qu'il se sent bien \n Poids corporel supérieur au poids recommandé"
    },
    {
        "patient_id": NumberLong(6),
        "full_name": "Trace Ross",
        "note": "Le patient déclare qu'il se sent bie"
    },
    {
        "patient_id": NumberLong(7),
        "full_name": "Claire Wilson",
        "note": "Le patient déclare qu'il se réveille souvent avec une raideur articulaire \n Il se plaint également de difficultés pour s’endormir \n Poids corporel supérieur au poids recommandé \n Tests de laboratoire indiquant un taux de cholestérol LDL élevé"
    },
    {
        "patient_id": NumberLong(8),
        "full_name": "Max Buckland",
        "note": "Les tests de laboratoire indiquent que les anticorps sont élevés \n Hémoglobine A1C supérieure au niveau recommandé"
    },
    {
        "patient_id": NumberLong(9),
        "full_name": "Natalie Clark",
        "note": "Le patient déclare avoir de la difficulté à se concentrer sur ses devoirs scolaires \n Hémoglobine A1C supérieure au niveau recommandé"
    },
    {
        "patient_id": NumberLong(9),
        "full_name": "Natalie Clark",
        "note": "Le patient déclare qu'il s’impatiente facilement en cas d’attente prolongée \n Il signale également que les produits du distributeur automatique ne sont pas bons \n Tests de laboratoire signalant des taux anormaux de cellules sanguines"
    },
    {
        "patient_id": NumberLong(9),
        "full_name": "Natalie Clark",
        "note": "Le patient signale qu'il est facilement irrité par des broutilles \n Il déclare également que l'aspirateur des voisins fait trop de bruit \n Tests de laboratoire indiquant que les anticorps sont élevés"
    },
    {
        "patient_id": NumberLong(10),
        "full_name": "Piers Bailey",
        "note": "Le patient déclare qu'il n'a aucun problème"
    },
    {
        "patient_id": NumberLong(10),
        "full_name": "Piers Bailey",
        "note": "Le patient déclare qu'il n'a aucun problème \n Taille incluse dans la fourchette concernée \n Hémoglobine A1C supérieure au niveau recommandé"
    },
    {
        "patient_id": NumberLong(10),
        "full_name": "Piers Bailey",
        "note": "Le patient déclare qu'il n'a aucun problème \n Poids corporel supérieur au poids recommandé \n Le patient a signalé plusieurs épisodes de vertige depuis sa dernière visite"
    },
    {
        "patient_id": NumberLong(10),
        "full_name": "Piers Bailey",
        "note": "Le patient déclare qu'il n'a aucun problème \n Tests de laboratoire indiquant une microalbumine élevée"
    }
]);