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
    number = random.uniform(1111, 9999)
    actual_cpu1 = random.randint(1,100);
    predicted_cpu1 = random.randint(1,100);
    actual_cpu2 = random.randint(1,100);
    predicted_cpu2 = random.randint(1,100);
    actual_cpu3 = random.randint(1, 100);
    predicted_cpu3 = random.randint(1, 100);
    actual_cpu4 = random.randint(1, 100);
    predicted_cpu4 = random.randint(1, 100);
    mse = random.random();
    my_date_time = datetime.datetime.now()
    epoch = my_date_time.timestamp()

    # my_data = f'{{"timestamp": "{my_date_time}", "id": "{number}", "epoch": "{epoch}",' \
    #     f' "cpu_real_vm1": "{actual_cpu1}", "cpu_predicted_vm1": "{predicted_cpu1}",' \
    #     f' "cpu_real_vm2": "{actual_cpu2}", "cpu_predicted_vm2": "{predicted_cpu2}", ' \
    #     f' "cpu_real_vm3": "{actual_cpu3}", "cpu_predicted_vm3": "{predicted_cpu3}", ' \
    #     f' "cpu_real_vm4": "{actual_cpu4}", "cpu_predicted_vm4": "{predicted_cpu4}", ' \
    #     f' "mse": "{mse}"}}'

    my_data = f'{{"payload": "timestamp: {my_date_time}, epoch: {epoch}, actual_cpu1: {actual_cpu1}, predicted_cpu1: {predicted_cpu1}, ' \
        f'actual_cpu2: {actual_cpu2}, predicted_cpu2: {predicted_cpu2}, actual_cpu3: {actual_cpu3}, predicted_cpu3: {predicted_cpu3}, actual_cpu4: {actual_cpu4}, predicted_cpu4: {predicted_cpu4}, ' \
        f'mse: {mse}"}}'
    source_data = json.loads(my_data)
    producer.send('monitoring-new', source_data)
    print(source_data)
    print("Successfully sent message")
    x += 5