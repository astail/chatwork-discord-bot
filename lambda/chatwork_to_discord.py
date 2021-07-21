import json
import requests
def lambda_handler(event, context):
    jsonItem = json.dumps(event)
    j = json.loads(jsonItem)
    webhook_body = eval(j["body"])
    account_id = webhook_body["webhook_event"]["account_id"]
    webhook_text = webhook_body["webhook_event"]["body"]

    webhook_url = 'https://discord.com/api/webhooks/1234567890/Abcdefghijkasdasdasdsadsadsadsadsadasdsadsadasdsadasdadsadasdasdsads'
    main_content = {
        "content":  "account_id(" + str(account_id) + "): " + webhook_text
    }

    if account_id != 5065302:
        requests.post(webhook_url,main_content)

    return {
        'statusCode': 200,
    }
