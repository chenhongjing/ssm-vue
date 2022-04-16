import { createStore } from 'vuex'
import user from '@/store/modules/user'
import actions from '@/store/actions'
import mutations from '@/store/mutations'


const store = createStore({
    modules: {
        user
    },
    // actions 和 mutations 暂时为空
    actions,
    mutations
})

export default store
