# SamplaApi

A simple API created to act as a backend for SamplaApp. It allows for sample and reseach management.
Current endpoints:
## GET
#### Research
`/researches` - returns list of researches in brief <br/>
`/researches/paginated` - returns paginated list of researches in brief <br/>
`/researches/{researchId}` - returns all information about certain research <br/>
#### Sample
`/researches/{researchId}/samples` - returns list of samples of a certain research in brief <br/>
`/researches/{researchId}/samples/paginated` - returns paginated list of samples with the provided researchId in brief <br/> 
`/researches/samples/{sampleId}` - returns all information about certain sample <br/> 
## POST
`/researches` - adds research with provided parameters and gives response of created entity at `/researches/{researchId}` <br/>
`/researches/{researchId}` - adds sample with provided parameters and assigns it to the provided researchId <br/>
## PATCH
`/researches/{researchId}` - updates research with provided parameters (requires only parameters with changes) <br/>
`/researches/samples/{sampleId}` -  updates sample with provided parameters (requires only parameters with changes) <br/>
## DELETE
`/researches/{researchId}` - deletes research with researchId <br/>
`/researches/samples/{sampleId}` - deletes sample with sampleId <br/><br/>
In the future, the API will be expanded to include registration, authentication and authorization as well as attatching files and adding todo lists to researches.
