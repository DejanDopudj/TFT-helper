import { axiosInstance } from './axiosService';

export async function createGame(successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/game')
  .then(res => {
    if (res.data) successCallback(res.data)
    else errorCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

export async function getGame(id, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/get-game', { id })
  .then(res => {
    if (res.data) successCallback(res.data)
    else errorCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

export async function incrementPlayerLevel(gameId, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/increase-level', { id: gameId })
  .then(res => {
    if (res.data === true) successCallback()
    else errorCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

export async function decrementPlayerLevel(gameId, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/decrease-level', { id: gameId })
  .then(res => {
    if (res.data === true) successCallback()
    else errorCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

export async function changePlayerHp(gameId, value, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/set-hp', { id: gameId, value: value })
  .then(res => {
    if (res.data === true) successCallback()
    else errorCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

export async function changePlayerGold(gameId, value, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/set-gold', { id: gameId, value: value })
  .then(res => {
    if (res.data === true) successCallback()
    else errorCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

export async function changeOtherPlayer(gameId, otherPlayer, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/change-other-player', { 
    id: otherPlayer.id,
    hp: otherPlayer.hp,
    level: otherPlayer.level,
    gold: otherPlayer.gold,
    gameId: gameId
   })
  .then(res => {
    successCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

export async function submitAugments(gameId, augmentNames, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/add-augments', { id: gameId, augmentNames: augmentNames })
  .then(res => {
    if (res.data === true) successCallback()
    else errorCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

export async function submitComponent(gameId, componentName, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/add-component', { id: gameId, componentName })
  .then(res => {
    if (res.data === true) successCallback()
    else errorCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}