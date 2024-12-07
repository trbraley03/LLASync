import json
import uuid

def is_valid_uuid(val):
    try:
        uuid.UUID(str(val))
        return True
    except ValueError:
        return False

def load_valid_uuids(file_path):
    with open(file_path, 'r') as file:
        return [line.strip() for line in file.readlines()]

def replace_invalid_or_duplicate_uuids(json_data, valid_uuids):
    existing_uuids = set()
    updated = False

    def validate_and_replace_uuid(json_object, key):
        nonlocal updated
        if key in json_object:
            current_uuid = json_object[key]
            if not is_valid_uuid(current_uuid) or current_uuid in existing_uuids:
                new_uuid = valid_uuids.pop(0)
                print(f"Replacing UUID {current_uuid} with {new_uuid}")
                json_object[key] = new_uuid
                updated = True
            existing_uuids.add(json_object[key])

    for language in json_data.get("languages", []):
        validate_and_replace_uuid(language, "UUID")
        for game in language.get("games", []):
            validate_and_replace_uuid(game, "UUID")
            for text in game.get("text", []):
                validate_and_replace_uuid(text, "UUID")
            for question in game.get("questions", []):
                validate_and_replace_uuid(question, "UUID")

    return updated

def main():
    games_data_file = 'C:/Users/zagsk/Documents/LLASync/demo/src/main/resources/com/learner/game/gamesData.json'
    valid_uuids_file = 'C:/Users/zagsk/Documents/LLASync/demo/src/main/resources/com/learner/game/v4_uuids.txt'

    valid_uuids = load_valid_uuids(valid_uuids_file)

    with open(games_data_file, 'r', encoding='utf-8') as file:
        json_data = json.load(file)

    if replace_invalid_or_duplicate_uuids(json_data, valid_uuids):
        with open(games_data_file, 'w', encoding='utf-8') as file:
            json.dump(json_data, file, indent=4)
        print("Updated the JSON file with new UUIDs.")
    else:
        print("No invalid or duplicate UUIDs found.")

if __name__ == "__main__":
    main()