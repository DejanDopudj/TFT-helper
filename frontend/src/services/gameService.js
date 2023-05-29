import { axiosInstance } from './axiosService';

export async function incrementPlayerLevel(player, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/increase-level', { id: player.id })
  .then(res => {
    console.log(res)
    successCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

export async function decrementPlayerLevel(player, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/decrease-level', { id: player.id })
  .then(res => {
    console.log(res)
    successCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

export async function changePlayerHp(player, value, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/set-hp', { id: player.id, value: value })
  .then(res => {
    console.log(res)
    successCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

export async function changePlayerGold(player, value, successCallback, errorCallback) {
  axiosInstance({ requiresAuth: true }).post('/api/game/set-gold', { id: player.id, value: value })
  .then(res => {
    console.log(res)
    successCallback()
  })
  .catch(err => {
    console.log(err)
    errorCallback()
  })
}

