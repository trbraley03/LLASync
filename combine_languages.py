import json

def combine_languages(json_data):
    combined_languages = {}
    
    for language in json_data.get("LANGUAGES", []):
        language_name = language.get("LANG")
        if language_name not in combined_languages:
            combined_languages[language_name] = {
                "LANG": language_name,
                "UUID": language.get("UUID"),
                "GAMES": []
            }
        combined_languages[language_name]["GAMES"].extend(language.get("GAMES", []))
    
    return {"LANGUAGES": list(combined_languages.values())}

def main():
    games_data_file = 'C:/Users/zagsk/Documents/LLASync/demo/src/main/resources/com/learner/game/gamesData.json'

    with open(games_data_file, 'r', encoding='utf-8') as file:
        json_data = json.load(file)

    combined_data = combine_languages(json_data)

    with open(games_data_file, 'w', encoding='utf-8') as file:
        json.dump(combined_data, file, indent=4)
    print("Combined languages into single instances.")

if __name__ == "__main__":
    main()