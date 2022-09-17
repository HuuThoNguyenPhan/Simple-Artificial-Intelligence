import pyttsx3
import datetime

import pywhatkit
import speech_recognition as sr
import webbrowser as wb


friday = pyttsx3.init() #Khởi tạo đối tượng Friday
voices = friday.getProperty('voices') #Lấy giọng
friday.setProperty('voice', voices[1].id) #Chọn giọng nói(Nam hoặc Nữ)


def speak(audio):
    print('F.R.I.D.A.Y: ' + audio)
    friday.say(audio)
    friday.runAndWait()


def time():
    Time = datetime.datetime.now().strftime("%I:%M:%p")
    speak("It is")
    speak(Time)


def welcome():
    # Chao hoi
    hour = datetime.datetime.now().hour
    if hour >= 6 and hour < 12:
        speak("Good Morning Sir!")
    elif hour >= 12 and hour < 18:
        speak("Good Afternoon Sir!")
    elif hour >= 18 and hour < 24:
        speak("Good Evening sir")
    speak("How can I help you,boss")


def command():
    c = sr.Recognizer() #Nhận giọng nói
    with sr.Microphone() as source: #Nhận giọng nói từ Microphone
        c.pause_threshold = 2 #Dừng 2s trước khi nghe lệnh mới
        audio = c.listen(source) #audio sẽ bằng thông tin nghe được từ Microphone
    try:
        query = c.recognize_google(audio, language='en-US')
        print("Người dùng: " + query)
    except sr.UnknownValueError:
        print('Sorry sir! I didn\'t get that! Try typing the command!')
        query = str(input('Your order is: '))
    return query


if __name__ == "__main__":
    welcome()

    while True:
        query = command().lower() # Các câu lệnh sẽ được viết thường để dễ ghi lại

        #Chức năng tìm kiếm google
        if "google" in query:
            speak("What should I search,boss")
            search = command().lower()
            url = f"https://google.com/search?q={search}"
            wb.get().open(url)
            speak(f'Here is your {search} on google')

        #Chức năng phát nhạc theo yêu cầu
        elif "music" in query:
            speak("What's music name?,boss")
            search = command().lower()
            pywhatkit.playonyt(search)
            speak(f'Here is your {search} on youtube')

        #Thoat khi nói quit
        elif "quit" in query:
            speak("Friday is off. Goodbye boss")
            quit()

        #Thông báo thời gian
        elif 'time' in query:
            time()

