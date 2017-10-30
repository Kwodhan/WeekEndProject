# WeekEndProject
Une API Rest de gestion de Week-End
  
## Diagramme

![Alt text](/taa.png?raw=true "Diagramme")

## Usage with WeekEndFront
Front app : https://github.com/Kwodhan/WeekEndFront

#### 1. Build back
cd WeekEndProject/   
mvn package -DskipTests     
docker build --tag "weekend" .  

#### 2. Build front
cd WeekEndFront/   
docker build --tag "front" .     

#### 3. Build All
cd WeekEndFront/    
or     
cd WeekEndProject/    
docker-composer up  

## Log

Les logs rassemblent toutes les requetes sauf les GET.   
Fichier : Log.log, au même emplacement que le jar   
Configuration : log4j2.xml, au même emplacement que le jar    

## Test

Test sur la bdd HSQL en localhost

## User 
Représente un utilisateur

#### Post 
| URL                                   |  Description|
| -------------------------------------------- | --------- |
| /WeekEndProject/api/persons/{id}/activities/  |      crée une nouvelle liste d'activité pour la personne {id}  |
| /WeekEndProject/api/persons/{id}/homes/       |      crée une nouvelle liste de logement pour la personne {id} |

#### Put
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/persons/{id}/activities/{idA}|ajoute l'activité {idA} à la liste de la personne {id}|
|/WeekEndProject/api/persons/{id}/homes/{idH}|ajoute le logement {idH} à la liste de la personne {id}|

#### Delete
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/persons/{id}/activities/{idA}|supprime l'activité {idA} de la liste de la personne {id}|
|/WeekEndProject/api/persons/{id}/homes/{idH}|supprime le logement {idH} de la liste de la personne {id}|

## Activity
Représente un sport ou un loisir abstrait. Chaque Activity comporte une liste de bonne condition méteo
#### Get
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/activities/{id}|récupére les information de l'activité {id}|
|/WeekEndProject/api/activities/leisures/|récupére la liste des loisirs|
|/WeekEndProject/api/activities/sports/|récupére la liste des sports|


## Location
Représente une localisation géographique
#### Get
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/locations/{id}|récupére les information de la localisation {id}|
|/WeekEndProject/api/locations/|récupére la liste des localisation|

#### Post
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/locations/|crée une localisation|


## Site
Représente un club de sport, une association, une plage, etc... 
#### Get
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/sites/{id}|récupére les informations du site {id}|

#### Post
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/sites/|crée un site|
|/WeekEndProject/api/sites/{id}/activities/| crée une nouvelle liste d'activité pour le site {id}|

#### Put
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/sites/|met à jour un site (activités compris)|
|/WeekEndProject/api/sites/{id}|met à jour un site (activités compris)|
|/WeekEndProject/api/sites/{id}/activities/{idA}|ajoute l'activité {idA} à la liste du site {id}|

#### Delete
| URL                                   |  Description|
| -------------------------------------------- | --------- |
|/WeekEndProject/api/sites/| supprime un site|
|/WeekEndProject/api/sites/{id}| supprime un site|
|/WeekEndProject/api/sites/{id}/activities/{idA}|supprime l'activité {idA} de la liste du site {id}|

## Spring Security
Authenfication : avec Spring security en mode  _Basic_


| Role                                   |  Description| Authorisation|
| -------------------------------------------- | --------- |--------- |
|ROLE_USER| Choisie ses activités et ses localisations| |
|ROLE_GERANT| Ajoute des sites| /sites/ |



 











