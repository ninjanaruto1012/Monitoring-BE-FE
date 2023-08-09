from kafka import KafkaConsumer
import json

consumer = KafkaConsumer('monitoring-actual-predicted-mmse-topic',
                         bootstrap_servers=['localhost:9092'],
                         value_deserializer=lambda m: json.loads(m.decode('ascii')),
                         auto_offset_reset='earliest')

for message in consumer:
    payload = message.value
    print(payload)