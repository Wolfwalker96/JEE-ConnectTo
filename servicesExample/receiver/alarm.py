from flask import Flask
app = Flask(__name__)


def sound():
    from playsound import playsound
    playsound("../ressources/allgemeineralarm.mp3")


@app.route("/tick")
def tick():
    print("Alerte Générale!")
    sound()
    return "Alerte Générale!"


if __name__ == "__main__":
    app.run(port=40000)
