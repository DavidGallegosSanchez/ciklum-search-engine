const indexProject = {
    "mappings": {
        "dynamic": true,
        "fields": {
            "name": [
                {
                    "type": "autocomplete"
                },
                {
                    "type": "token"
                }
            ],
            "tags": [
                {
                    "type": "string"
                },
                {
                    "type": "token"
                }
            ]
        }
    }
}

db = db.getSiblingDB('search_engine')

db.projects.createSearchIndex(
    "dyn_idx0",
    indexProject)