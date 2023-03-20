# SamplaApi

Simple API created to serve as backend for SamplaApp. It allows for sample and reseach management.
Current endpoints:
## GET
`/researches` - returns paginated list of researches in brief
`/researches/{researchId}` - returns all information about certain research
`/researches/samples/{sampleId}` - returns all information about certain sample
## POST
`/researches` - adds research with provided parameters and gives response of created entity at `/researches/{researchId}`
`/researches/{researchId}` - adds sample with provided parameters and assigns it to the provided researchId
## PATCH
`/researches/{researchId}` - updates research with provided parameters (requires only parameters with changes)
`/researches/samples/{sampleId}` -  updates sample with provided parameters (requires only parameters with changes)
## DELETE
`/researches/{researchId}` - deletes research with researchId 
`/researches/samples/{sampleId}` - deletes sample with sampleId
