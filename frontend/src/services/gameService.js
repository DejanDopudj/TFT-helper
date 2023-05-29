import { axiosInstance } from './axiosService';

export async function incrementPlayerLevel(player, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/increase-level', { id: player.id })
  .then(res => {
    if (res.data === true) successCallback()
    else errorCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

export async function decrementPlayerLevel(player, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/decrease-level', { id: player.id })
  .then(res => {
    if (res.data === true) successCallback()
    else errorCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

export async function changePlayerHp(player, value, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/set-hp', { id: player.id, value: value })
  .then(res => {
    if (res.data === true) successCallback()
    else errorCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

export async function changePlayerGold(player, value, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/set-gold', { id: player.id, value: value })
  .then(res => {
    if (res.data === true) successCallback()
    else errorCallback()
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
