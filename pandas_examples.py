# -*- coding: utf-8 -*-
"""Pandas Examples.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1adrn4jI_8bIOLyF5XNVKhbQzTbqXjyYp
"""

people={
    "first":["Corey","Jane","John"],
    "last":["Schafer","Doe","Doe"],
    "email":["coreymschaefer@gmail.com","janedoe@yahoo.com","johndoe@hotmail.com"]
}

import pandas as pd
df=pd.DataFrame(people)

df

people["email"]

df['email']

#rows of data; Series is a single column of rows
type(df['email'])

#dataframe is a container for multiple series objects

df.email

#accessing multiple columns. We will pass a list inside the list
#Since this has multiple columns now, we can no longer call this a Series
df[['last','email']]

#to see all of columns
df.columns

#iloc is integer location
#Series containing the first row of data
df.iloc[0]

#1st and 2nd row
df.iloc[[0,1]]

#specifying the columns we want
df.iloc[[0,1],2]

df

df.loc[0]

df.loc[[0,1]]

#there is a difference between loc and iloc
df.loc[[0,1],'email']

df.loc[[0,1],['email','last']]

#setting a specific column as index values instead of automated 0,1,...n
df.set_index('email')

#dataframe is not actually changed
df

#we want to actually set our index and have that change carry over to the actual dataframe
df.set_index('email',inplace=True)

df

df.index

df.loc['janedoe@yahoo.com']

#what if i want a specific column's value of a specific index
df.loc['janedoe@yahoo.com','last']

#df.loc[0] will not work anymore as we do not have the default row index anymore
df.iloc[0]

#we can bring back our default index
df.reset_index(inplace=True)

df

#we want dataframe with lastname as 'Doe'
df['last']=='Doe'

filt= (df['last']=='Doe')

df[filt]

#df.loc[...] is used to look up rows and cols by labels
#we can also passa set of booleans to filter data out
df.loc[filt]

df.loc[filt,'email']

filt=(df['last']=='Doe') & (df['first']=='John')
df.loc[filt,'email']

filt=(df['last']=='Schafer') | (df['first']=='John')
df.loc[filt,'email']

#we do not want rows that has lastname Schafer and firstname as john
df.loc[~filt,'email']

df.columns

df

df.columns=['email','first_name','last_name']

df

#UpperCase Comprehension
df.columns = [x.upper() for x in df.columns]
df

df.columns = df.columns.str.replace('_',' ')
df

df.columns = df.columns.str.replace(' ','_')
df.columns = [x.lower() for x in df.columns]
df

df.rename(columns={"first_name":"first","last_name":"last"})
df

df.rename(columns={"first_name":"first","last_name":"last"},inplace=True)
df

#relpacing John's last name with Smith
df.loc[2]

df.loc[2]=["johnsmith@email.com",'John',"Smith"]

df

#what if there are too many columns
df.loc[2,['last','email']]
df

df.loc[2,['last','email']]=['Green','johngreen@email.com']
df

#Another attribute for changing a single column value
df.at[2,'last']='Doe'
df.at[2,'email']='johndoe@email.com'
df

df['email'].str.upper()

df['email']=df['email'].str.upper()
df

df['email']=df['email'].str.lower()
df

#apply,applymap,map,replace
#Apply
#want to see the length of all our email IDs
df['email'].apply(len)

def update_email(email):
  return email.upper()

df['email'].apply(update_email)

df['email']=df['email'].apply(update_email)
df

df['email']=df['email'].apply(lambda x: x.lower())
df

df['email'].apply(len)

df.apply(len) #number of rows in each column

len(df['email'])

df.apply(len,axis='columns')

df.apply(pd.Series.min)

df.apply(lambda x:x.min())

#applymap only works on dataframes
#unlike apply which works on both Series and DataFrames
#applymap runs apply function to every individual element in our dataframe
df.applymap(len)

df.applymap(str.lower)

#map
df['first'].map({"Corey":"Chris","Jane":"Mary"})
#the ones that are not replaced in the 'first' column
#will be NaN

df

df['first'].replace({"Corey":"Chris","Jane":"Mary"})

df['first']+' '+df['last']

df['full_name'] = df['first']+' '+df['last']
df

df.drop(columns=['first','last'],inplace=True)

df['full_name'].str.split(' ',expand=True)

df[['first','last']]=df['full_name'].str.split(' ',expand=True)

df

#add a single row
df.append({'first':'Tony'},ignore_index=True)

people={
    "first":["Tony","Steve"],
    "last":["Stark","Rogers"],
    "email":["IronMan@avenge.com","Cap@avenge.com"]
}
df2=pd.DataFrame(people)
df2

#add df2 to df
df.append(df2,ignore_index=True)

df.append(df2,ignore_index=True,sort=False)

#we have no inplace in this
df=df.append(df2,ignore_index=True,sort=False)

df

#remove rows
df.drop(index=4)

#remove whose last name is Doe
filt= df['last']=='Doe'
df.drop(index = df[filt].index)

people={
    "first":["Corey","Jane","John","Adam"],
    "last":["Schafer","Doe","Doe","Doe"],
    "email":["coreymschaefer@gmail.com","janedoe@yahoo.com","johndoe@hotmail.com","A@email.com"]
}
df=pd.DataFrame(people)

df

df.sort_values(by='last')

df.sort_values(by='last',ascending=False)

df.sort_values(by=['last','first'],ascending=False)

df.sort_values(by=['last','first'],ascending=[False,True])

df.sort_values(by=['last','first'],ascending=[False,True],inplace=True)

df

df.sort_index()

df['last'].sort_values()

import numpy as np
import pandas as pd

people = {
    'first': ['Corey', 'Jane', 'John', 'Chris', np.nan, None, 'NA'],
    'last': ['Schafer', 'Doe', 'Doe', 'Schafer', np.nan, np.nan, 'Missing'],
    'email': ['CoreyMSchafer@gmail.com', 'JaneDoe@email.com', 'JohnDoe@email.com', None, np.nan, 'Anonymous@email.com', 'NA'],
    'age': ['33', '55', '63', '36', None, None, 'Missing']
}

df=pd.DataFrame(people)
df

df.dropna()

#default arguments
#since it is set to index, it looks at rows. We can change it to cols
#drop rows with any missing values
df.dropna(axis='index',how='any')

df.dropna(axis='index',how='all')

df.dropna(axis='columns',how='all')

df.dropna(axis='columns',how='any')

#we want rows that atleast their email IDs filled in
df.dropna(axis='index',how='any',subset=['email'])

#email or last should be there
df.dropna(axis='index',how='all',subset=['email','last'])

people = {
    'first': ['Corey', 'Jane', 'John', 'Chris', np.nan, None, 'NA'],
    'last': ['Schafer', 'Doe', 'Doe', 'Schafer', np.nan, np.nan, 'Missing'],
    'email': ['CoreyMSchafer@gmail.com', 'JaneDoe@email.com', 'JohnDoe@email.com', None, np.nan, 'Anonymous@email.com', 'NA'],
    'age': ['33', '55', '63', '36', None, None, 'Missing']
}

df=pd.DataFrame(people)

df.replace('NA',np.nan,inplace=True)
df.replace('Missing',np.nan,inplace=True)
df

df.dropna()

df.isna()

df.fillna('MISSING')

#casting datatypes
df

df.dtypes

#df['age'].mean() will throw an error bcs age is object(string) datatype
#we cannot typecast age to int bcs we have NaN and NaN is treated as float
type(np.nan)

df['age'] = df['age'].astype(float)

df

type(df['age'])

df.dtypes

df['age'].mean()

#df.astype()
