require('dotenv').config();
const { Kafka } = require("kafkajs");


async function testListen() {
    const kafka = new Kafka({ brokers: [process.env.URL_KAFKA] });
    // If you specify the same group id and run this process multiple times, KafkaJS
    // won't get the events. That's because Kafka assumes that, if you specify a
    // group id, a consumer in that group id should only read each message at most once.
    const consumer = kafka.consumer({ groupId: "" + Date.now() });

    await consumer.connect();

    await consumer.subscribe({ topic: "device.heartbeat", fromBeginning: true });
    await consumer.run({
        eachMessage: async (data) => {
            console.log(data.message.value.toString());
        }
    });
}
const kafka = new Kafka({ brokers: [process.env.URL_KAFKA] });
const producer = kafka.producer();
producer.connect();

exports.sendMessage = async (topic, message) =>{

    await producer.send({
        topic: topic,
        messages: [
            message,
        ]
    });
}
exports.listenToMessage = async (topic, consumerGroupId, callback) =>{
    const kafka = new Kafka({ brokers: [process.env.URL_KAFKA] });
    // If you specify the same group id and run this process multiple times, KafkaJS
    // won't get the events. That's because Kafka assumes that, if you specify a
    // group id, a consumer in that group id should only read each message at most once.
    const consumer = kafka.consumer({ groupId: consumerGroupId });
    await consumer.connect();
    await consumer.subscribe({ topic: topic, fromBeginning: false });
    await consumer.run({
        eachMessage: async (data) => {
            callback(JSON.parse(data.message.value));
        }
    });
}
// testListen().then(() => console.log("Done"), err => console.log(err));