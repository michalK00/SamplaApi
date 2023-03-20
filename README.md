# SamplaApi

Simple API created to serve as backend for SamplaApp. It allows for sample and reseach management.
Current endpoints:
## GET
`/researches` - returns paginated list of researches in brief <br/>
`/researches/{researchId}` - returns all information about certain research <br/>
`/researches/samples/{sampleId}` - returns all information about certain sample <br/> 
## POST
`/researches` - adds research with provided parameters and gives response of created entity at `/researches/{researchId}` <br/>
`/researches/{researchId}` - adds sample with provided parameters and assigns it to the provided researchId <br/>
## PATCH
`/researches/{researchId}` - updates research with provided parameters (requires only parameters with changes) <br/>
`/researches/samples/{sampleId}` -  updates sample with provided parameters (requires only parameters with changes) <br/>
## DELETE
`/researches/{researchId}` - deletes research with researchId <br/>
`/researches/samples/{sampleId}` - deletes sample with sampleId <br/>
