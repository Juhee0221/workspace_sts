#pip install flask 파이썬 웹
#pip install APScheduler 스케줄링
#pip install flask_apscheduler
# 프로그램 종료 : Ctrl + c

from flask import Flask
from flask_apscheduler import APScheduler

import requests
import xmltodict
import json
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import bs4

class Config:
    SCHEDULER_API_ENABLED = True

app = Flask(__name__)
app.config.from_object(Config())

scheduler = APScheduler()
scheduler.init_app(app)
scheduler.start()

@app.route("/")
def hello():
    return "Hello World!"

#seconds=60*3 반복되는 시간(초)
#hours=2 2시간 마다
@scheduler.task('interval', id='scheduler_1', hours=1, misfire_grace_time=None)
# @scheduler.task('interval', id='scheduler_1', seconds=60, misfire_grace_time=None)
def scheduler_1():
    get_API_Data()

# 디비작업
def aaa():
# 공통 #
    import pymysql
    import pandas as pd

    team_df = pd.read_csv('TeamAPI.csv', encoding='cp949')
    team_df

    # idxList = []

    # for idx, each in enumerate(team_df['이산화탄소 (ppm)']):
    #     if not each.isdigit():
    #         idxList.append(idx)

    # idxList

    # team_df.drop(index=idxList, inplace=True)

    team_df.isnull().sum()

    team_df.index = range(len(team_df))
# 지선영 #
    team_df.loc[(pd.to_numeric(team_df['소음(dB)']) >= 0), '소음 등급'] = '매우좋음'
    team_df.loc[(pd.to_numeric(team_df['소음(dB)']) > 30) & (pd.to_numeric(team_df['소음(dB)']) <= 40), '소음 등급'] = '좋음'
    team_df.loc[(pd.to_numeric(team_df['소음(dB)']) > 40) & (pd.to_numeric(team_df['소음(dB)']) <= 60), '소음 등급'] = '보통'
    team_df.loc[(pd.to_numeric(team_df['소음(dB)']) > 60) & (pd.to_numeric(team_df['소음(dB)']) <= 80), '소음 등급'] = '나쁨'
    team_df.loc[(pd.to_numeric(team_df['소음(dB)']) > 80), '소음 등급'] = '매우나쁨'
# 윤상혁 #
    team_df['미세먼지 등급'] = '보통'

    team_df.loc[(pd.to_numeric(team_df['미세먼지 (㎍/㎥)']) >= 0) & (pd.to_numeric(team_df['미세먼지 (㎍/㎥)']) <= 30), '미세먼지 등급'] = '좋음'
    team_df.loc[(pd.to_numeric(team_df['미세먼지 (㎍/㎥)']) > 30) & (pd.to_numeric(team_df['미세먼지 (㎍/㎥)']) <= 80), '미세먼지 등급'] = '보통'
    team_df.loc[(pd.to_numeric(team_df['미세먼지 (㎍/㎥)']) > 80) & (pd.to_numeric(team_df['미세먼지 (㎍/㎥)']) <= 150), '미세먼지 등급'] = '나쁨'
    team_df.loc[pd.to_numeric(team_df['미세먼지 (㎍/㎥)']) > 150, '미세먼지 등급'] = '매우 나쁨'

    team_df['초미세먼지 등급'] = '보통'

    team_df.loc[(pd.to_numeric(team_df['초미세먼지 (㎍/㎥)']) >= 0) & (pd.to_numeric(team_df['초미세먼지 (㎍/㎥)']) <= 15), '초미세먼지 등급'] = '좋음'
    team_df.loc[(pd.to_numeric(team_df['초미세먼지 (㎍/㎥)']) > 15) & (pd.to_numeric(team_df['초미세먼지 (㎍/㎥)']) <= 35), '초미세먼지 등급'] = '보통'
    team_df.loc[(pd.to_numeric(team_df['초미세먼지 (㎍/㎥)']) > 35) & (pd.to_numeric(team_df['초미세먼지 (㎍/㎥)']) <= 75), '초미세먼지 등급'] = '나쁨'
    team_df.loc[pd.to_numeric(team_df['초미세먼지 (㎍/㎥)']) > 75, '초미세먼지 등급'] = '매우 나쁨'

    team_df['통합실내지수 등급'] = '보통'

    team_df.loc[(pd.to_numeric(team_df['통합실내지수']) >= 0) & (pd.to_numeric(team_df['통합실내지수']) < 50), '통합실내지수 등급'] = '매우 나쁨'
    team_df.loc[(pd.to_numeric(team_df['통합실내지수']) >= 50) & (pd.to_numeric(team_df['통합실내지수']) < 80), '통합실내지수 등급'] = '나쁨'
    team_df.loc[(pd.to_numeric(team_df['통합실내지수']) >= 80) & (pd.to_numeric(team_df['통합실내지수']) < 90), '통합실내지수 등급'] = '보통'
    team_df.loc[(pd.to_numeric(team_df['통합실내지수']) >= 90) & (pd.to_numeric(team_df['통합실내지수']) <= 100), '통합실내지수 등급'] = '좋음'

    # 이산화탄소의 평균
    team_df['이산화탄소 (ppm)'] = pd.to_numeric(team_df['이산화탄소 (ppm)']) # 새로 실행하면 실행해줘야 함
    avg_co2 = round(team_df['이산화탄소 (ppm)'].mean(), 2)

    # 이산화탄소 NaN 데이터에 평균 데이터 넣기
    team_df['이산화탄소 (ppm)'] = team_df['이산화탄소 (ppm)'].fillna(avg_co2)

    # 휘발성유기화합물의 평균
    team_df['휘발성유기화합물 (ppb)'] = pd.to_numeric(team_df['휘발성유기화합물 (ppb)']) # 새로 실행하면 실행해줘야 함
    avg_vocs = round(team_df['휘발성유기화합물 (ppb)'].mean(), 2)

    # 휘발성유기화합물 NaN 데이터에 평균 데이터 넣기
    team_df['휘발성유기화합물 (ppb)'] = team_df['휘발성유기화합물 (ppb)'].fillna(avg_vocs)
# 김주희 #
    team_df['온도(℃)'] = pd.to_numeric(team_df['온도(℃)'])
    team_df['습도(%)'] = pd.to_numeric(team_df['습도(%)'])

# INSERT START
    conn = pymysql.connect(host='localhost', user='studyroom', password='studyroom', db='studyroom', charset='utf8')
    cursor = conn.cursor(pymysql.cursors.DictCursor)

    #
    SERIAL_NO = team_df['시리얼번호']
    DATA_TIME = team_df['데이터 시간']
    #
    PM_CODE = pd.to_numeric(team_df['미세먼지 (㎍/㎥)'])
    PM_GRADE = team_df['미세먼지 등급']
    FPM_CODE = pd.to_numeric(team_df['초미세먼지 (㎍/㎥)'])
    FPM_GRADE = team_df['초미세먼지 등급']
    CO2_CODE = pd.to_numeric(team_df['이산화탄소 (ppm)'])
    VOCS_CODE = pd.to_numeric(team_df['휘발성유기화합물 (ppb)'])
    CICI_CODE = pd.to_numeric(team_df['통합실내지수'])
    CICI_GRADE = team_df['통합실내지수 등급']

# 선영 INSERT #
    # DECIBEL_INFO 데이터 추가
    DECIBEL = pd.to_numeric(team_df['소음(dB)'])
    DECIBEL_GRADE = team_df['소음 등급']

    # 데이터 전체 삭제
    deleteTable = ["DECIBEL_INFO", "AIR_QUALITY", "CICI", "TEMANDHUM"]
    for i in range(len(deleteTable)):
        deleteAll = f"DELETE FROM {deleteTable[i]}"
        cursor.execute(deleteAll)

    conn.commit()

    for i in range(len(team_df)):
        # 선영 #
        decibelData = f"INSERT INTO DECIBEL_INFO (DECIBEL, DECIBEL_TIME, DECIBEL_GRADE, SERIAL_NO) VALUES ({DECIBEL[i]}, '{DATA_TIME[i]}', '{DECIBEL_GRADE[i]}', '{SERIAL_NO[i]}')"
        # 상혁 #
        airData = f"INSERT INTO AIR_QUALITY (PM_CODE, PM_GRADE, FPM_CODE, FPM_GRADE, CO2_CODE, VOCS_CODE, AIR_TIME, SERIAL_NO) VALUES ({PM_CODE[i]}, '{PM_GRADE[i]}', {FPM_CODE[i]}, '{FPM_GRADE[i]}', {CO2_CODE[i]}, {VOCS_CODE[i]}, '{DATA_TIME[i]}','{SERIAL_NO[i]}')"
        ciciData = f"INSERT INTO CICI (CICI_CODE, CICI_GRADE, CICI_TIME, SERIAL_NO) VALUES ({CICI_CODE[i]}, '{CICI_GRADE[i]}', '{DATA_TIME[i]}','{SERIAL_NO[i]}')"
        # 주희 #
        sql=f"INSERT INTO TEMANDHUM (DATE_TIME, SERIAL_NO, TEMPERATURE, HUMIDITY) VALUES ('{team_df['데이터 시간'][i]}', '{team_df['시리얼번호'][i]}', {team_df['온도(℃)'][i]}, {team_df['습도(%)'][i]})"
        #execute 실행
        cursor.execute(decibelData)
        cursor.execute(airData)
        cursor.execute(ciciData)
        cursor.execute(sql)



    # commit 필수
    conn.commit()
    
    conn.close()



#####################################################################
def get_API_Data():
    print('get_API_Data() 실행 중')    
    #API 데이터 불러오기
    url = 'http://apis.data.go.kr/3210000/SeochoIaqSvc/getSeochoIaqRtData'
    params ={'serviceKey' : 'p9FZA94eZhl6jAWkF6ip6U+rwBfUIb04JLuw/nvI6vK4DQgImm0nvtI1QDsJPyJwLv54TedGckoIMDYuKLHYVQ=='
            , 'numOfRows' : '90'
            , 'pageNo' : '1' }

    response = requests.get(url, params=params)

    dict_res=xmltodict.parse(response.content)
    json_string=json.dumps(dict_res['response']['body']['items'],ensure_ascii=False)

    jsonObj=json.loads(json_string)
    df=pd.DataFrame(jsonObj['item'])
    df.columns=['데이터 시간','시리얼번호','미세먼지 (㎍/㎥)','초미세먼지 (㎍/㎥)','이산화탄소 (ppm)','휘발성유기화합물 (ppb)','온도(℃)','습도(%)','소음(dB)','통합실내지수']

    print(df)

    # 파일의 권한을 읽기/쓰기 권한으로 변경
    import os
    os.chmod("TeamAPI.csv", 0o777)

    df.to_csv('TeamAPI.csv', index=False, mode='a', encoding='utf-8-sig', header=False)
    #df.to_csv('TeamAPI_test2.csv', index=False, mode='w', encoding='utf-8-sig')
    #df.to_csv(path_or_buf='D:\01-STUDY\\dev\\python_study\\DB_connect\\TeamAPI.csv' , index=False, mode='a', encoding='utf-8-sig', header=False)


    aaa()

    print('get_API_Data() 끝')

#####################################################################

if __name__ == '__main__':
    count = 1
    app.run(port=80)


