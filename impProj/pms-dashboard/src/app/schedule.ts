import { User } from "./User"

export class Schedule
{
    schedule_id:number | undefined
    patient:User | undefined 
    doctor:User | undefined
    title: string | undefined
    date: string | undefined
    from_time:string | undefined
    to_time:string| undefined
    status: string | undefined
    reason: string | undefined
    created_at:string | undefined
    created_by: string | undefined
    updated_at: string | undefined
    updated_by: string | undefined
    is_active: number|undefined
}
