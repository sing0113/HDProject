import {login,getInfo,logout} from '@/api/login'
import {getToken,setToken,removeToken} from '@/utils/auth'

const user = {
  state: {
    token: getToken(),
    name: '',
    avatar: '',
    roles: []
  },
  mutations: {
    SET_TOKEN: (state,token) => {
      state.token = token;
    },
    SET_NAME: (state,name) => {
      state.name = name;
    },
    SET_AVATAR: (state,avatar) => {
      state.avatar = avatar;
    },
    SET_ROLES: (state,roles) => {
      state.roles = roles;
    }
  },
  actions: {
    //登录
    Login({commit},userInfo) {
      const name = userInfo.userName.trim();
      const password = userInfo.password.trim();
      return new Promise((resolve,reject) => {
        login(name,password).then(response => {
          const data = response.data;
          const tokenStr = data.tokenHead+data.token;
          setToken(tokenStr);
          commit('SET_TOKEN',tokenStr);
          resolve();
        }).catch(error => {
          reject(error);
        })
      })
    },

    //获取用户信息
    GetInfo({commit,state}) {
      return new Promise((resolve,reject) => {
        getInfo().then(response => {
          const data = response.data;
          if (data.roles && data.roles.length > 0) {
            commit('SET_ROLES', data.roles);
            commit('SET_NAME',data.username);
            commit('SET_AVATAR',data.icon);
            resolve(response);
          } else {
            reject('getInfo: roles must be a non-null array!')
          }
        }).catch(error => {
          reject(error);
        })
      })
    },

    //用户退出登录
    Logout({commit,state}) {
      return new Promise((resolve, reject) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN','');
          commit('SET_ROLES',[]);
          removeToken();
          resolve();
        }).catch(error => {
          reject(error);
        })
      })
    },

    //前端 登出
    FedLogOut({commit}) {
      return new Promise(resolve => {
        commit('SET_TOKEN','');
        removeToken();
        resolve();
      })
    }
  }
}

export default user
