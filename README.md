# WeekEndProject
Une application de gestion des Week Ends

# Use API

## Person 
Représente un utilisateur
#### Get
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/persons/{id}| récupére les informations de la personne {id} |

#### Post 
| URL                                   |  Description|
| -------------------------------------------- | --------- |
| /WeekEndProject/api/persons/      		    |      crée une personne |
| /WeekEndProject/api/persons/{id}/activities/  |      crée une nouvelle liste d'activité pour la personne {id}  |
| /WeekEndProject/api/persons/{id}/homes/       |      crée une nouvelle liste de logement pour la personne {id} |

#### Put
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/persons/|met à jour une personne (activités et logements compris)|
|/WeekEndProject/api/persons/{id}| met à jour une personne (activités et logements compris)|
|/WeekEndProject/api/persons/{id}/activities/{idA}|ajoute l'activité {idA} à la liste de la personne {id}|
|/WeekEndProject/api/persons/{id}/homes/{idH}|ajoute le logement {idH} à la liste de la personne {id}|

#### Delete
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/persons/| supprime une personne|
|/WeekEndProject/api/persons/{id}| supprime une personne|
|/WeekEndProject/api/persons/{id}/activities/{idA}|supprime l'activité {idA} de la liste de la personne {id}|
|/WeekEndProject/api/persons/{id}/homes/{idH}|supprime le logement {idH} de la liste de la personne {id}|

## Activity
Représente un sport ou un loisir abstrait. 
#### Get
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/activities/{id}|récupére les information de l'activité {id}|
|/WeekEndProject/api/activities/leisures/|récupére la liste des loisirs|
|/WeekEndProject/api/activities/sports/|récupére la liste des sports|

#### Post
| URL                                   |  Description|
| -------------------------------------------- | --------- |
/WeekEndProject/api/activities/| crée une nouvelle activité|

#### Put
| URL                                   |  Description|
| -------------------------------------------- | --------- |
/WeekEndProject/api/activities/| met à jour une activité |
/WeekEndProject/api/activities/{id}|met à jour une activité |

#### Delete
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/activities/| supprime une activité|
|/WeekEndProject/api/activities/{id}| supprime une activité|

## Location
Représente une localisation géographique
### Get
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/locations/{id}|récupére les information de la localisation {id}|

### Post
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/locations/|crée une localisation|

### Put
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/locations/|  met à jour une localisation|
|/WeekEndProject/api/locations/{id}| met à jour une localisation|

### Delete
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/locations/| supprime une localisation|
|/WeekEndProject/api/locations/{id}|supprime une localisation|

## Site
Représente un club de sport, une association, une plage, etc... 
### Get
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/sites/{id}|récupére les informations du site {id}|

### Post
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/sites/|crée un site|
|/WeekEndProject/api/sites/{id}/activities/| crée une nouvelle liste d'activité pour le site {id}|

### Put
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/sites/|met à jour un site (activités compris)|
|/WeekEndProject/api/sites/{id}|met à jour un site (activités compris)|
|/WeekEndProject/api/sites/{id}/activities/{idA}|ajoute l'activité {idA} à la liste du site {id}|

### Delete
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/sites/| supprime un site|
|/WeekEndProject/api/sites/{id}| supprime un site|
|/WeekEndProject/api/sites/{id}/activities/{idA}|supprime l'activité {idA} de la liste du site {id}|
