from kafka import KafkaProducer
from time import sleep
import json, os
import random
import datetime
import string

producer = KafkaProducer(bootstrap_servers=['localhost:9092'],
                         value_serializer=lambda m: json.dumps(m).encode('ascii'))
x = 5
while(True):
    sleep(5)
    number = random.randint(1111, 9999)
    actual_cpu1 = random.randint(1,100);
    predicted_cpu1 = random.randint(1,100);
    actual_cpu2 = random.randint(1,100);
    predicted_cpu2 = random.randint(1,100);
    actual_cpu3 = random.randint(1, 100);
    predicted_cpu3 = random.randint(1, 100);
    actual_cpu4 = random.randint(1, 100);
    predicted_cpu4 = random.randint(1, 100);
    my_date_time = datetime.datetime.now()
    epoch = my_date_time.timestamp()

    # my_data = f'{{"timestamp": "{my_date_time}", "id": "{number}", "epoch": "{epoch}",' \
    #     f' "cpu_real_vm1": "{my_value}", "cpu_predicted_vm1": "{my_value_2}",' \
    #     f' "cpu_real_vm2": "{my_value_3}", "cpu_predicted_vm2": "{my_value_4}"}}'

    my_data = f'{{"payload": "timetamp: {my_date_time}, actual_cpu1: {actual_cpu1}, predicted_cpu1: {predicted_cpu1}, ' \
        f'actual_cpu2: {actual_cpu2}, predicted_cpu2: {predicted_cpu2}, ' \
        f'actual_cpu3: {actual_cpu3}, predicted_cpu3: {predicted_cpu3}, ' \
        f'actual_cpu4: {actual_cpu4}, predicted_cpu4: {predicted_cpu4}"}}'
    source_data = json.loads(my_data)
    producer.send(' ', source_data)
    print("Successfully sent message")
    x += 5